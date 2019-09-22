package com.twu67.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCard {
  private String ccNumber;
  private String csc;
  private String expiry;

  public String toString(Double amount) {
    return String.format("<authorisation-request>\n" +
      "\t<cc_number>%s</cc_number>\n" +
      "\t<csc>%s</csc>\n" +
      "\t<expiry>%s</expiry>\n" +
      "\t <amount>%f</amount>\n" +
      "</authorisation-request>", getCcNumber(), getCsc(), getExpiry(), amount);
  }
}
