package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.dto.MessageDto;
import com.openclassrooms.chatop.dto.ResponseDto;
import com.openclassrooms.chatop.exception.ResourceNotFoundException;
import com.openclassrooms.chatop.service.MessageService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> sendMessage(@Valid @RequestBody MessageDto message) throws ResourceNotFoundException {
        log.info("POST api/messages called -> start the process to send a new message");
        ResponseDto response = ResponseDto.builder().message("Message send with success").build();
        this.messageService.sendMessage(message);
        log.info("Message send successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
