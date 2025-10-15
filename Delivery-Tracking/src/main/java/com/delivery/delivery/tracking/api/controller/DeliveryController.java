package com.delivery.delivery.tracking.api.controller;

import com.delivery.delivery.tracking.api.model.CourierIdInput;
import com.delivery.delivery.tracking.api.model.DeliveryInput;
import com.delivery.delivery.tracking.domain.model.Delivery;
import com.delivery.delivery.tracking.domain.repository.DeliveryRepository;
import com.delivery.delivery.tracking.domain.service.DeliveryCheckpointService;
import com.delivery.delivery.tracking.domain.service.DeliveryPreparationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryPreparationService deliveryPreparationService;
    private final DeliveryCheckpointService deliveryCheckpointService;

    private final DeliveryRepository deliveryRepository;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery draft(@RequestBody @Valid DeliveryInput input) {
        {
            return deliveryPreparationService.draft(input);
        }
    }

    @PutMapping("/{deliveryId}")
    public Delivery edit(@PathVariable UUID deliveryId, @RequestBody @Valid DeliveryInput input) {
        {
            return deliveryPreparationService.edit(deliveryId, input);
        }
    }

    @GetMapping
    public PagedModel<Delivery> findAll(@PageableDefault Pageable pageable) {
        /*Outra falha forcada para testar o Circuit Breaker da API Gateway
        * if(Math.random < 0.7){
        * throw new RunTimeException();
        * }
        */


        /* Esse trecho é pra simular o TimeOutPattern, adicionando "problemas" na execucao no metodo
        int millis = new Random().nextInt(400);
        Thread.sleep(millis);
        return new PageModel<>(deliveryRepository.findAll(pageable));

        Precisar adicionar a anotação @SneakyThrows no método
        *
        *
        * */
        return new PagedModel<>(
                deliveryRepository.findAll(pageable));
    }

    @GetMapping("/{deliveryId}")
    public Delivery findById(@PathVariable UUID deliveryId){
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{deliveryId}/placement")
    public void place(@PathVariable UUID deliveryId) {
        deliveryCheckpointService.place(deliveryId);
    }

    @PostMapping("/{deliveryId}/pickups")
    public void pickup(@PathVariable UUID deliveryId,
                       @Valid @RequestBody CourierIdInput input) {
        deliveryCheckpointService.pickup(deliveryId, input.getCourierId());
    }

    @PostMapping("/{deliveryId}/completion")
    public void complete(@PathVariable UUID deliveryId) {
        deliveryCheckpointService.complete(deliveryId);
    }
}
