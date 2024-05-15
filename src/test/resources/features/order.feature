# language: pt

Funcionalidade: Order
	Cenario: Inserir Order
		Quando registrar um novo Order
		Então é registrado com sucesso
		
	Cenario: Buscar Order
		Dado que um order ja foi inserido
		Quando efetuar a busca dele
		Então order é retornado com sucesso
	
	Cenario: Atualizar Order
		Dado que um order ja foi inserido
		Quando atualizar um novo Order
		Então é atualizado com sucesso
		
	Cenario: Remover Order
		Dado que um order ja foi inserido
		Quando a requisição for para remover um order
		Então order é removido com sucesso
		
	Cenário: Listar orders existentes
    	Dado que um order ja foi inserido
    	Quando requisitar a lista de order
    	Então os orders são exibidas com sucesso

 