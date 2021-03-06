angular.module("HelpApp", [])
.value('urlBase', 'http://localhost:8080/javabe/webapi/')
.controller("ChamadoController", function($http, urlBase){
	
	var self= this;
	self.usuario='Eduardo Baia';
	
	self.chamado = undefined;
	self.chamados=[];
	
	self.salvar = function(){
		var metodo= 'POST';
		if(self.chamado.id){
			metodo='PUT';
		}
		
		$http({
			method: metodo,
			url: urlBase+ 'chamados/',
			data: self.chamado
		}).then(function successCallback(response){
			self.atualizarTabela();
		}, function errorCallback (response){
			self.ocorreuErro();
		});
		
		
	};
	
	
    self.novo = function(chamado){
		 self.chamado={};
		 
	};
	
	
    self.alterar = function(chamado){
		this.chamado =chamado; 
	};
	
	
	self.deletar = function(chamado){
		self.chamado=chamado;
		
		
		$http({
			method: 'DELETE',
			url: urlBase+ 'chamados/'+ self.chamado.id+'/',
			data: self.chamado
		}).then(function successCallback(response){
			self.atualizarTabela();
		}, function errorCallback (response){
			self.ocorreuErro();
		});
		self.activate();
	};
	
	
	
	self.concluir = function(chamado){
		self.chamado= chamado;
		
		$http({
			method: "PUT",
			url: urlBase+'chamados/'+ self.chamado.id+'/'
		}).then(function sucessCallback(response){
			self.atualizarTabela();
		}, function errorCallback(response){
			self.ocorreuErro();
		});
		
	};
	
	
	self.ocorreuErro = function(){
		alert("Ocorreu erro inesperado");
	};
	
	
	self.atualizarTabela = function (){
		
		$http({
			method: 'GET',
			url: urlBase+'chamados/'
		
		}).then(function successCallback(response){
			self.chamados=response.data;
			console.log("Entrou em sucesso "+ self.chamado.nome);
			self.chamado= undefined;
			
		}, function errorCallback (response){
			self.ocorreuErro();
			console.log("Entrou em fracaso ");
		});

		
	};
	
	
	
	self.activate = function (){
		self.atualizarTabela();
	}
	
	self.activate();
});


