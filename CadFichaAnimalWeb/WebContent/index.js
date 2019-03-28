function novaFicha() {
    //var where   = "Teste";
    window.location = "novaFicha.html" //+ "?t1=" + where;
}


function atualizaAnimais() {
    window.location = "atualizaAnimais.html" //+ "?t1=" + where;
}


function atualizaFicha(idFicha) {
    window.location = "atualizaFicha.html" + "?idFicha=" + idFicha;
}


var app = angular.module('ListaApp', []);
var serviceURI = "/CadFichaAnimalWeb/rest/Ficha/";

app.controller('ListaController', function($scope , $http) {

            //mock
            $scope.listaFichaMock = [
                { id: 1, data: "01/02/2019", status: "Ativo" },
                { id: 2, data: "12/02/2019", status: "Ativo" },
                { id: 4, data: "21/02/2019", status: "Inativo" },
                { id: 6, data: "11/03/2019", status: "Ativo" },
                { id: 7, data: "17/03/2019", status: "Ativo" },
               ];

            getFichas($scope,$http);


            function getFichas($scope,$http){
                $http.get(serviceURI+"consultaListaFichas").success(function(data){
                    $scope.listaFicha = data;
                }).error(function(error){
                    alert("Some Error Occurred!" + error);
                });
            }
            
            $scope.filtraLista = function () {

                var idFiltro = document.getElementById("idFiltro").value;
                var dataInicial = document.getElementById("dataInicialFiltro").value;
                var dataFinal = document.getElementById("dataFinalFiltro").value;

                //alert( "idFiltro: " + document.getElementById("idFiltro").value + " - data Inicial: " + document.getElementById("dataInicialFiltro").value + " - data Final: " + document.getElementById("dataFinalFiltro").value);
                
                if (idFiltro != "")
                { filtraListaId ( idFiltro , $scope , $http); }
                else if (dataInicial != "" && dataFinal != "") 
                { filtraListaData ( dataInicial , dataFinal , $scope , $http); }
                else 
                { getFichas( $scope,$http ); }

            };

            $scope.filtraListaMock = function () {
                
                $scope.listaFicha = [
                { id: 1, data: "01/02/2019", status: "Ativo" },
                { id: 2, data: "12/02/2019", status: "Ativo" },
                { id: 6, data: "11/03/2019", status: "Ativo" },
                { id: 7, data: "17/03/2019", status: "Ativo" },
               ];
            };

            filtraListaId = function (idFicha , $scope , $http ) {
                
                //alert("Entrou no filtro id!");

                $http.get(serviceURI+"filtraFichaId?idFicha=" + idFicha).success(function(data){
                    $scope.listaFicha = data;
                }).error(function(error){
                    alert("Some Error Occurred!" + error);
                });
            };

            filtraListaData = function (dataInicial , dataFinal , $scope , $http) {
                
                $http.get(serviceURI+"filtraFichaData?dataInicial=" + dataInicial + "&dataFinal=" + dataFinal).success(function(data){
                $scope.listaFicha = data;
                }).error(function(error){
                    alert("Some Error Occurred!" + error);
                });
            };

            $scope.deletarFichaLista = function (Id) {
                
                $http.get(serviceURI+"deletaFicha?idFicha=" + Id).success(function(data){
                
                }).error(function(error){
                    alert("Some Error Occurred!" + data);
                    return;
                });
                alert("Ficha " + Id + " excluída com sucesso");
                getFichas( $scope,$http );

            };

            $scope.editarFichaLista = function (Id) {
                
                atualizaFicha(Id);

            };


});

