angular.module('factoryIncludeSearchCustomersServices', ['ngResource'])
    .factory('includeSearchCustomersServices', function ($resource) {

        return $resource('rest/customerservice/includesearchcustomer/:json', null, {
            'save': {
                method: 'GET',
                headers: { "Content-Type": "application/json;charset=utf-8" },
                hasBody: false
            }
        });
    }) 
    .factory("includeSearchServiceCustomer", function (includeSearchCustomersServices, $q, $rootScope) {
        var service = {};
        var customerIncludeSearch = 'customerIncludeSearch';
        service.serviceFactory = function (customer, flag) {
            return $q(function (resolve, reject) {

                if (flag == 'search') {
                    includeSearchCustomersServices.query(customer, function (dados) {
                        $rootScope.$broadcast(customerIncludeSearch);
                        
                        resolve({
                            dadosConsulta: dados,
                            mensagem: 'Clientes foram consultados com sucesso',
                            search: true
                        });
                    }, function (erro) {
                        reject({
                            dadosConsulta: erro,
                            mensagem: 'Clientes não consultados com sucesso',
                            search: false
                        });
                    });

                } else {
                    includeSearchCustomersServices.save({json: customer.coding},customer, function (dados) {
                        $rootScope.$broadcast(customerIncludeSearch);

                        resolve({
                            mensagem: 'Cliente cadastrado com sucesso',
                            inclusao: dados.confim
                        });
                    }, function (erro) {
                        
                        resolve({
                            mensagem: 'Cliente não foi cadastrado!',
                            inclusao: dados.confim
                        });
                    });
                }
            });
        };

        return service;
    });
