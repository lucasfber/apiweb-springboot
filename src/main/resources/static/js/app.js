var app = angular.module('ApiWebApp', []);

app.controller('IndexController', function($scope, $http){
	$scope.listaJogos = [];
	$scope.busca = "";
	
	$scope.listaClientes = [];
	$scope.cliente = {};
	
	$scope.cadastraCliente = function(cliente){
		$http.post('http://localhost:8080/clientes', cliente).then(function(resposta){
			delete $scope.cliente;
			console.log(resposta);
			$scope.listaClientes.push(resposta.data);
			
		}, function(erro){
			console.log(erro);
		});
	};
	
	
	$scope.listarClientes = function(){
		$http.get('http://localhost:8080/clientes').then(function(resposta){
			
			$scope.listaClientes = resposta.data;
		});
	};
	
	$scope.deletarCliente = function(cliente){
		$http.delete('http://localhost:8080/clientes/' + cliente.id).then(function(resposta){
			var posicao = $scope.listaClientes.indexOf(cliente);
			$scope.listaClientes.splice(posicao, 1);
			console.log(resposta.data);
		});
	};
			
	$scope.listarClientes();
});
	