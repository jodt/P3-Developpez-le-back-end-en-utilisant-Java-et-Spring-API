package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.dto.MessageDto;
import com.openclassrooms.chatop.exception.ResourceNotFoundException;
import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final RentalService rentalService;

    private final UserService userService;

    public MessageServiceImpl(MessageRepository messageRepository, RentalService rentalService, UserService userService) {
        this.messageRepository = messageRepository;
        this.rentalService = rentalService;
        this.userService = userService;
    }


    @Override
    public Message sendMessage(MessageDto messageDto) throws ResourceNotFoundException {
        log.info("Try sending message for rental with id {} by user with id {}", messageDto.getRentalId(), messageDto.getUserId());
        Rental rental = this.rentalService.getRentalById(messageDto.getRentalId());
        User user = this.userService.getUserById(messageDto.getUserId());
        LocalDate now = LocalDate.now();

        Message newMessage = Message.builder()
                .user(user)
                .rental(rental)
                .message(messageDto.getMessage())
                .updatedAt(now)
                .createdAt(now)
                .build();

        return this.messageRepository.save(newMessage);
    }
}
