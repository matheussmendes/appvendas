package com.appvendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.appvendas.model.MetaDeVendaMensal;

@RequestMapping
@Controller
public class MetaMensalController {

	@Autowired(required = true)
	private MetaDeVendaMensal metaMensal;
	
	@ModelAttribute("metaMensal")
	public double mostrarMetaMensal() {
		System.out.println("VALOR DA META::::" +metaMensal.getValorDaMeta());
		return metaMensal.getValorDaMeta();
	}
}
