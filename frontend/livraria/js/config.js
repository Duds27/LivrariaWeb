angular.module('SharedServices', [])
    .config(function ($httpProvider) {
        $httpProvider.interceptors.push('myHttpInterceptor');
        var requestFunction = function (data, headersGetter) {
            // todo start the spinner here
            return data;
        };
        var responseFunction = function (data, headersGetter) {
            return data;
        };
        $httpProvider.defaults.transformRequest.push(requestFunction);
        $httpProvider.defaults.transformResponse.push(responseFunction);
    })
    // register the interceptor as a service, intercepts ALL angular ajax http calls
    .factory('myHttpInterceptor', function ($q, $window, $rootScope) {
        return function (promise) {
            return promise.then(function (response) {
                // do something on success
                // todo hide the spinner
                alert('stop success spinner');
                return response;

            }, function (response) {
                // do something on error
                // todo hide the spinner
                alert('stop error spinner');
                return $q.reject(response);
            });
        };
    });

(function () {
    'use strict';

    angular.module('livrariaApp', ['ngRoute', 'SharedServices'])
        .controller('MainController', function ($scope, $route, $routeParams, $location) {
            $scope.$route = $route;
            $scope.$location = $location;
            $scope.$routeParams = $routeParams;
        })
        .controller('LivrariaController', function ($scope, $rootScope, $routeParams, $http) {

            /* Recuperando todas os livros da livraria */

            $scope.data = [];

            $http.get('http://localhost:8080/LivrariaWeb/FrontController?action=Library').then(function successCallback(success) {

                var obj = success.data.librarys;

                for (var i = 0; i < obj.length; i++) {

                   var objectLibrary = {
                        library_id:     obj[i].library_id,
                        category_name:  obj[i].category_name,
                        book_title:     obj[i].book_title,
                        book_publisher: obj[i].book_publisher,
                        book_year:      obj[i].book_year,
                        author_name:    obj[i].author_name,
                        book_price:     obj[i].book_price,
                   };
                   $scope.data.push(objectLibrary);                    
                }        
                $scope.library = angular.copy($scope.data);
                $scope.enabledEdit = [];     

            }, function errorCallback(err) {
                console.log(err);
            });


            $scope.addLibrary = function () {
                var lib = {
                    category_name: "",
                    book_title: "",
                    book_publisher: "",
                    book_year: "",
                    author_name: "",
                    book_price: "",
                    disableEdit: false
                };
                $scope.library.push(lib);
                $scope.enabledEdit[$scope.library.length - 1] = true;
                $scope.enabledSub = true;
            }

            $scope.postLibrary = function(index) {

                console.log(angular.toJson($scope.library[$scope.library.length-1]));
                $http.post('http://localhost:8080/LivrariaWeb/FrontController?action=Library', angular.toJson($scope.library[$scope.library.length-1])).then(function successCallback(success) {

                    $scope.enabledEdit[$scope.library.length - 1] = false;
                    $scope.enabledSub = false;


                }, function errorCallback(err) {
                    console.log(err);
                });

            }

            $scope.editLibrary = function (index) {
                $scope.enabledEdit[index] = true;
            }

            $scope.cancelLibrary = function(index) {
                $scope.enabledSub = false;
                $scope.library.splice($scope.library.length-1, 1);
                $scope.enabledEdit[index] = false;
            }

            $scope.deleteLibrary = function (index) {

                $http.delete('http://localhost:8080/LivrariaWeb/FrontController?action=Library&library_id=' + $scope.library[index].library_id).then(function successCallback(success) {

                    $scope.library.splice(index, 1);

                }, function errorCallback(err) {
                    console.log(err);
                });

            }

            $scope.updateLibrary = function (index) {

                var count = 0;

                for (var i = 0; i < $scope.library.length; i++) {
                
                    console.log($scope.library[i].library_id);
                    if ($scope.library[i].library_id == $scope.library[index].library_id) {
                        count = i + 1;
                        break;
                    }
                }

                console.log("[PUT] -> " + angular.toJson($scope.library[count]));




                $http.put('http://localhost:8080/LivrariaWeb/FrontController?action=Library', angular.toJson($scope.library[count])).then(function successCallback(success) {

                    $scope.enabledEdit[$scope.library[count]] = true;


                }, function errorCallback(err) {
                    console.log(err);
                });


                $scope.enabledEdit[index] = false;
            }

        })
        .config(function ($routeProvider, $locationProvider) {
            $routeProvider
                .when('/Home', {
                    templateUrl: './view/livraria.html',
                    controller: 'LivrariaController'
                })
                .when('/', {
                    templateUrl: './view/livraria.html',
                    controller: 'LivrariaController'
                })
                .otherwise('/Home', {
                    templateUrl: './view/livraria.html'
                });

            // configure html5 to get links working on jsfiddle
            $locationProvider.html5Mode({
                enabled: true,
                requireBase: true
            });
        });

})(window.angular);