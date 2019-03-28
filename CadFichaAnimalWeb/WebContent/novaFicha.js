function voltarTelaInicial() {
    window.location = "index.html"
}

var app = angular.module('CadFichaApp', []);

    app.controller('CadFichaController', function($scope , $http) {
        
        
    $scope.dataMock = {
    model: null,
    size: '7',
    availableOptions: [
        {value: '1', name: 'Cão'},
        {value: '2', name: 'Gato'},
        {value: '3', name: 'Galinha'},
        {value: '4', name: 'Cavalo'},
        {value: '5', name: 'Usro'},
    ]};

    getAnimais($scope,$http);


    function getAnimais($scope,$http){
                $http.get(serviceURI+"listaAnimais").success(function(data){
                    $scope.data = data;
                }).error(function(error){
                    alert("Some Error Occurred!" + error);
                });
    }

    $scope.incluirFicha = function() {

                var obs = document.getElementById("idFiltro").value;
                $scope.idFicha = 0;
                
                $http.get(serviceURI+"adicionaFicha?obs=" + obs).success(function(data){
                    $scope.idFicha = data;
                }).error(function(error){
                    alert("Some Error Occurred!" + error);
                });

                alert ("Id ficha = " + $scope.idFicha);

                angular.forEach(Selected, function(val){
                    $scope.selectedValues.push( val.id.toString() );
                });

                //Falta Implementar
                //listaAnimais.forEach(item -> { 
                    //item.setIdFicha(ficha.getId());
                //    $scope.incluirAnimal($scope.idFicha, idAnimal);
			        //System.out.println("*** VIB *** item: idFicha: " + item.getIdFicha() + " - idAnimal: " + item.getIdAnimal());			
                //} );

        alert("Ficha Incluído com sucesso");
        voltarTelaInicial();
        return count;

    };

    $scope.incluirAnimal = function(idFicha, idAnimal) {
                    
                $http.get(serviceURI+"adicionaAnimal?idFicha=" + idFicha + "?idAnimal=" + idAnimal ).success(function(data){
                
                }).error(function(error){
                    alert("Some Error Occurred!" + error);
                });
		
    };

});