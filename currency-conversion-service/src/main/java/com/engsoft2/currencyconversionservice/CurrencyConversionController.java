package com.engsoft2.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

    private final CurrencyExchangeProxy proxy;
    private final RabbitTemplate rabbitTemplate;
    private final FanoutExchange fanoutExchange;

    public CurrencyConversionController(CurrencyExchangeProxy proxy,
                                        RabbitTemplate rabbitTemplate,
                                        FanoutExchange fanoutExchange) {
        this.proxy = proxy;
        this.rabbitTemplate = rabbitTemplate;
        this.fanoutExchange = fanoutExchange;
    }

    // *** VERSÃO RESTTEMPLATE – AGORA COM HOST DOCKER ***
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        // IMPORTANTE: dentro do Docker, usar o nome do serviço, não localhost
        ResponseEntity<CurrencyConversion> responseEntity =
                new RestTemplate().getForEntity(
                        "http://currency-exchange:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversion.class,
                        uriVariables
                );

        CurrencyConversion currencyConversion = responseEntity.getBody();

        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " rest template"
        );
    }

    // *** VERSÃO FEIGN + RABBITMQ (ROTEIRO 7) ***
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,
                                                               @PathVariable String to,
                                                               @PathVariable BigDecimal quantity) {

        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);

        // Monta DTO da mensagem
        HistoryDTO dto = new HistoryDTO(currencyConversion.getFrom(),
                currencyConversion.getTo());

        // Envia pro exchange fanout – routingKey vazio mesmo (fanout ignora)
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", dto);

        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " feign"
        );
    }
}