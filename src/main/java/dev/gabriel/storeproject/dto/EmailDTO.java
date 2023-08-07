package dev.gabriel.storeproject.dto;

import javax.validation.constraints.Email;

public record EmailDTO(@Email String email) {
}