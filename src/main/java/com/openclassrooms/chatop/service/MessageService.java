package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.dto.MessageDto;
import com.openclassrooms.chatop.exception.ResourceNotFoundException;
import com.openclassrooms.chatop.model.Message;

public interface MessageService {

    public Message sendMessage (MessageDto messageDto) throws ResourceNotFoundException;

}
