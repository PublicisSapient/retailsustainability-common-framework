package com.publicis.sapient.p2p.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;
/**
 * @author boinpsu
 *
 */
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String statusCode;
	@JsonInclude(Include.NON_NULL)
	private Map<String, String> errorMap;
	private String statusMessage;
	private Object output;
}