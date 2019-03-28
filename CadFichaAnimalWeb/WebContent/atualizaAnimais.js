function voltarTelaInicial() {
    window.location = "index.html"
}

var app = angular.module('AtualizaAnimaisApp', []);

app.controller('AtualizaAniaisController', function($scope, $html) {

            $scope.listaAnimalMock = [
                { Id: 1, Cod: "CAO01", Nome: "Cão" },
                { Id: 2, Cod: "GAT01", Nome: "Gato" },
                { Id: 3, Cod: "GAL01", Nome: "Galinha" },
                { Id: 4, Cod: "CAV03", Nome: "Cavalo" },
                { Id: 5, Cod: "AVE06", Nome: "Papagaio" }
               ];


            getAnimais($scope,$http);

            function getAnimais($scope,$http){
                    $http.get(serviceURI+"listaAnimais").success(function(data){
                        $scope.listaAnimal = data;
                    }).error(function(error){
                        alert("Some Error Occurred!" + error);
                    });
            }

            $scope.atualizaAnimal = function (Id, Cod, Nome) {
                
                alert("Animal " + Id + " atualizado com sucesso");
                atualizaFicha(Id, Cod, Nome);

            };


});

