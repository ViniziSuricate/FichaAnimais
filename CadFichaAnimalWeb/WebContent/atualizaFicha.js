function voltarTelaInicial() {
    window.location = "index.html"
}

var app = angular.module('AtualizaFichaApp', []);

    app.controller('AtualizaFichaController', function($scope, $http , $location, $routeParams) {
        
        alert("Carregando ficha $routParams: " + $routeParams.parm1);
        alert("Carregando ficha $location: " + $location.search("idFicha").myParam);
        $scope.Id = $routeParams.parm1;
        $scope.Id = $location.search("idFicha").myParam; 

        /*
        $scope.Id = "2";
        $scope.Obs = "Observação da Ficha 2";
        $scope.DataFicha = "20/03/2019";
        $scope.StatusSelect = { selected: 'Ativo' };
        */
        $scope.Status = ['Ativo','Inativo'] ;

        getFicha($scope,$http);

        function getFicha($scope,$http){
                    $http.get(serviceURI+"listaAnimais").success(function(data){
                        $scope.dadosFicha = data;
                    }).error(function(error){
                        alert("Some Error Occurred!" + error);
                    });

                    $scope.Obs = $scope.dadosFicha.Obs
                    $scope.DataFicha = $scope.dadosFicha.data
                    //falta implementar
                    $scope.StatusSelect = { selected: 'Ativo' };
                           
        }

        $scope.dataAnimaisMOck = [
            {value: 1, name: 'Cão'},
            {value: 2, name: 'Gato'},
            {value: 3, name: 'Galinha'},
            {value: 4, name: 'Cavalo'},
            {value: 5, name: 'Usro'}
        ];


        getAnimais($scope,$http);

        function getAnimais($scope,$http){
                    $http.get(serviceURI+"listaAnimais").success(function(data){
                        $scope.dataAnimais = data;
                    }).error(function(error){
                        alert("Some Error Occurred!" + error);
                    });
        }

        getAnimaisFicha($scope,$http);

        function getAnimaisFicha($scope,$http){
                    $http.get(serviceURI+"listaAnimaisFicha?idFicha=" + $scope.Id).success(function(data){
                        $scope.animaisSelected = data;
                    }).error(function(error){
                        alert("Some Error Occurred!" + error);
                    });
        }
        //$scope.animaisSelected = {dataAnimais:[1,4]};
        

        $scope.salvarFicha = function() {
            var count = 0;
            //angular.forEach(todoList.todos, function(todo) {
            //    count += todo.done ? 0 : 1;
            //  });
            alert("Usuário salvo com sucesso");
            voltarTelaInicial();
            return count;

        };




});