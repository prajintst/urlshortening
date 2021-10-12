package com.techgentsia.shorturl.exception.handler;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * POJO for keeping error details
 *
 * @author <a href="mailto:prajin@techgentsia.com">Prajin Prakash</a>
 * @version $Revision: 1.0 $
 */
@Setter
@Getter
@NoArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private Object details;

    public ErrorDetails(Date timestamp, String message, Object details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
