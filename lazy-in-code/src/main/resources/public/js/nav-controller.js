var app = angular.module('myApp', ['ngRoute']);

app.run(function($rootScope){
    $rootScope.userName=undefined;
});

    app.config(['$routeProvider', '$locationProvider',function($routeProvider,$locationProvider) {
        $locationProvider.html5Mode(true);
        $routeProvider.when('/', {
            templateUrl: 'html/index-home.html',
            controller: 'indexController'
        }).when('/signUp', {
            templateUrl: 'html/signup.html',
            controller: 'signController'
        }).when('/logIn', {
            templateUrl: 'html/login.html',
            controller: 'loginController'
        }).when('/cart', {
          templateUrl: 'html/cart.html',
          controller: 'cartController'
      }).when('/createProd', {
            templateUrl: 'html/product.html',
            controller: 'createProdController'
        }).when('/existProd', {
            templateUrl: 'html/userProduct.html',
            controller: 'existProdController'
        }).when('/updateProd', {
          templateUrl: 'html/updateProduct.html',
          controller: 'updateProdController'
      }).otherwise({ redirectTo: '/'});
    }]);

    app.controller('indexController',function($scope,$location){
      $scope.handleWelcome=function(){
          $location.path('/logIn');
      };
   });
 app.controller('signController',function($scope,$http,$location){
    $scope.handleUserAction=function(){
        var person={name:$scope.userName, password:$scope.userPassword, email:$scope.email};
        
        var url = 'http://localhost:8081/api/v2/signup', data = person;
        
          $http.post(url, data).then(function mySuccess(response) {
            $location.path('/logIn');
            // This function handles success
            }, function myError(response) {
            
            // this function handles error
            });
    };
 });

 app.controller('loginController',function($scope,$rootScope,$http,$location){

  $scope.handleDontHaveAccount=function(){
    $location.path('/signUp');
  };
    $scope.handleUserLoginAction=function(){
        
        var person={email:$scope.email, password:$scope.userPassword};

        var url = 'http://localhost:8081/api/v2/login', data = person;
        
          $http.post(url, data).then(function mySuccess(response) {
            if(response!=null){
            // This function handles success
            $rootScope.userName=response.data.id;
            localStorage.setItem('userid',response.data.id);
            $location.path('/existProd');
          }
            }, function myError(response) {
            
            // this function handles error
            });
            
    };
 });

app.controller('cartController',function($scope,$rootScope,$http,$location){
 var url = 'http://localhost:8081/api/v1/product';
 $http.get(url).then(function mySuccess(response) {
     $scope.listofProd = response.data;
   }, function myError(response) {
     console.log("response");
   });
   $scope.add2CartProduct=function(product){
     if($rootScope.userName==undefined){
        $location.path('/logIn');
     }else{
      url = 'http://localhost:8081/api/v3/cart';
      var data={userId:$rootScope.userName,prodId:product.id,count:1,price:parseFloat(product.price)}
      $http.post(url,data).then(function mySuccess(response) {
        console.log(response.data);
      }, function myError(response) {
        console.log("response");
      });


     }
   }

});

 app.controller('existProdController',function($scope,$rootScope,$http,$location,$route){
     if($rootScope.userName==undefined){
       $location.path('logIn')
     }else{
      var id=localStorage.getItem('userid');
      console.log(id);
     var url = 'http://localhost:8081/api/v3/cart/'+id;
     console.log(url);
     $http.get(url).then(function mySuccess(response) {
         console.log(response.data);
         $scope.listofProd = response.data;
       }, function myError(response) {
         console.log("response");
       });
 
       $scope.goToNewProd=function(){
         $location.path('/createProd');
       }
       $scope.updateProduct=function(product){
         localStorage.setItem('product',JSON.stringify(product));
         $location.path('/updateProd');
       }
       $scope.deleteProduct=function(product){
         console.log(product.id);
         var deleteUrl='http://localhost:8081/api/v3/cart/'+product.id;
         var data={}
         $http.delete(deleteUrl,data).then(function mySuccess(response){
           console.log('deleted data is '+response.data);
           $route.reload();
         },function myError(response) {
 
           // this function handles error
           
           });
       };
     }
 });


 app.controller('updateProdController',function($scope,$http,$location){
   var product=JSON.parse(localStorage.getItem('product'));

   $scope.prodName=product.prodName;
   $scope.author=product.author;
   $scope.price=product.price;
   $scope.brand=product.brand;
   $scope.design=product.design;
   

  $scope.handleUpdateProdAction=function(){
    var data={userId:product.userId,prodId:product.prodId,count:1,price:product.price};

    var url = 'http://localhost:8081/api/v3/cart';
        
          $http.post(url, data).then(function mySuccess(response) {
            $location.path('/existProd');
            // This function handles success
            console.log(response.data);
        }, function myError(response) {
            
            // this function handles error
            console.log('error')
            });
  }
   
});


 app.controller('createProdController',function($scope,$location,$http){
     
    $scope.handleCreateProdAction=function(){
      console.log(localStorage.getItem('userid'));
        var product={ userId:localStorage.getItem('userid'),count:1, perPrice:parseFloat($scope.price) ,prodName:$scope.prodName ,price:parseFloat($scope.price) ,genre:$scope.genre ,author:$scope.author ,type:$scope.prodtype ,brand:$scope.brand ,design:$scope.design};
        var url = 'http://localhost:8081/api/v3/cartInsert', data = product;
        
          $http.post(url, data).then(function mySuccess(response) {

            // This function handles success
            console.log(response.data);
            $location.path('/existProd');
        }, function myError(response) {
            
            // this function handles error
            console.log('error')
            });
    };
 });