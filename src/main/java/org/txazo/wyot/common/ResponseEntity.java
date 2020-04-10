package org.txazo.wyot.common;

import org.txazo.java.annotation.processing.Data;

import java.io.Serializable;

@Data
public class ResponseEntity<V> implements Serializable {

    private static final long serialVersionUID = -294837735881475575L;

    private int status;

    private V data;

    private String message;

}
