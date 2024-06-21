package br.fiap.techchallenge.api.producao.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fiap.techchallenge.api.producao.dto.OrderDTO;
import br.fiap.techchallenge.api.producao.util.FiapUtils;

@Service
public class OrderMessageListener implements IMessageListener{
	
	
	private static Logger logger = LogManager.getLogger(OrderMessageListener.class);
	
	
	@Autowired
	OrderService orderService;
	
			
	@RabbitListener(queues = {"${queue1.name}"})
	@Override
	public void receive(String message) {
		OrderDTO orderDTO = null;
		try {
			logger.info("Mesagem recebida! ".concat(message));
			 orderDTO = FiapUtils.converToOrderDTO(message);
			 logger.info(orderDTO);

			 if(orderDTO != null)
				 orderService.postOrder(orderDTO);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
			
	}
	
}
