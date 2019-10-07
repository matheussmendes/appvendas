package com.appvendas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

import com.appvendas.service.UsuarioServiceImpl;

@Controller
public class EmailController {
	/*
	 * Desenvolvedor: Matheus Mendes
	 * 
	 * suportetecnologia@outlook.com.br
	*/
	//@Autowired
	//private JavaMailSender mailSender;

	@Autowired
	private EmpreendimentoController controllerDoEmpreendimento;

	@Autowired
	private UsuarioServiceImpl serviceDoUsuario;

	public  boolean emailEnviado = false;
	
	/*
	 * public String enviarEmail() {
	 * 
	 * SimpleMailMessage mensagemDoEmail = new SimpleMailMessage();
	 * 
	 * mensagemDoEmail.setSubject("Serviço de notificação da Plataforma nirvana");
	 * mensagemDoEmail.setText("Parabéns, " +
	 * serviceDoUsuario.capturarNomeDaEmpresaLogada(serviceDoUsuario.
	 * capturarIdDaEmpresaLogada()) +
	 * " ! Você acabou de superar as despesas deste mês. A partir de agora começará a ter rentabilidade."
	 * +
	 * " Reforce suas vendas e/ou prestação de serviços para aumentá-la. Boa sorte!!!"
	 * );
	 * mensagemDoEmail.setTo(controllerDoEmpreendimento.retornarEmailDaEmpresaLogada
	 * ()); mensagemDoEmail.setFrom("emailaqui");
	 * 
	 * try { mailSender.send(mensagemDoEmail); emailEnviado = true; return
	 * "email enviado com sucesso!!!";
	 * 
	 * }
	 * 
	 * catch (Exception ex) { ex.printStackTrace(); return
	 * "erro ao tentar enviar o email"; }
	 * 
	 * }
	 * 
	 * PARA O ENVIO DO E-MAIL FUNCIONAR, CONFIGURE ESSA PROPRIEDADE NO ARQUIVO APLICATION PROPERTIES
	 *  # MAIL

 spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=emailaqui
spring.mail.password=senhadoemailaqui
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.ssl.enable=false
spring.mail.test-connection=true


	 */
}
