package com.example.presentation.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/versionado")
public class VersionadoResource {
	@GetMapping(path = "/path/{version}", version = "v1")
	public String path1() {
		return "Path - version 1";
	}

	@GetMapping(path = "/path/{version}", version = "v2")
	public String path2() {
		return "Path - version 2";
	}

	@GetMapping(path = { "/path/v1/comun", "/path/v2/comun" })
	public String path() {
		return "Path - version 1 y 2";
	}

	@Hidden
	@GetMapping(path = "/query", version = "1")
	public String query1() {
		return "Query Params - version 1";
	}

	@Hidden
	@GetMapping(path = "/query", version = "2")
	public String query2() {
		return "Query Params - version 2";
	}

	@Operation(parameters = {
			@Parameter(in = ParameterIn.QUERY, name = "version", required = false, description = "Version del API", schema = @Schema(type = "string")) })
	@GetMapping(path = "/query")
	public String query() {
		return "Query Params - version 1 y 2";
	}
	
	@Hidden
	@GetMapping(path = "/header", version = "1")
	public String header1() {
		return "header - version 1";
	}

	@Hidden
	@GetMapping(path = "/header", version = "2")
	public String header2() {
		return "Header - version 2";
	}

	@Operation(parameters = {
			@Parameter(in = ParameterIn.HEADER, name = "x-api-version", required = false, description = "Version del API", schema = @Schema(type = "string")) })
	@GetMapping("/header" )
	public String header() {
		return "Header - version 1 y 2";
	}

}
