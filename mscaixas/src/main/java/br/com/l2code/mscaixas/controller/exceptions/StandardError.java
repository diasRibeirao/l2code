package br.com.l2code.mscaixas.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@Setter
@Getter
public class StandardError implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer status;
    private String msg;
    private Long timeStamp;
}
