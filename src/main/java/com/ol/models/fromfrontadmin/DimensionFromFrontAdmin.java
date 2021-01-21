package com.ol.models.fromfrontadmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DimensionFromFrontAdmin {

	private Integer id;
	private Integer x;
	private Integer y;
	private Integer z;
	private Integer poid;
}
