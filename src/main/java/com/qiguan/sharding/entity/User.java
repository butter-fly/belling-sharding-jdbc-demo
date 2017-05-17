/**
 * 
 */
package com.qiguan.sharding.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Administrator
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1409727754463794083L;

	private Integer id;
	private Integer userId;
	private String name;
	private Integer age;
}
