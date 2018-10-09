
angular.module('customerWeb')
    .controller('FormIncludeSearchCustomersController',
    function ($scope, $rootScope, $location,$filter, includeSearchServiceCustomer) {

        $scope.mock = [{
            "codigo":1,
            "razao":"OLX do Brasil",
            "nome":" Vendas de produtos Ltda. ",
            "cpfCnpj":"01.001.001/0001-88",
            "telefone":"43434343",
            "status":"Ativo"
        },
        {
            "codigo":2,
            "razao":"Mercado Livre do Brasil",
            "nome":" Vendas de produtos Ltda. ",
            "cpfCnpj":"01.001.002/0001-88",
            "telefone":"43434343",
            "status":"Ativo"
        }];

        $scope.customer = {};

        $scope.include = function () {

            $scope.customer.nome = $scope.nome;
            $scope.customer.razao = $scope.razao;
            $scope.customer.cpfcnpj = $scope.cpfcnpj;
            $scope.customer.telefone = $scope.telefone;

            includeSearchServiceCustomer.serviceFactory($scope.conces, '')
                .then(function (dados) {

                })
                .catch(function (erro) {

                });
        }

        $scope.search = function () {
            includeSearchServiceCustomer.serviceFactory($scope.conces, 'search')
                .then(function (dados) {
                    if (dados.retorno)
                        $scope.montDynamicTable(dados.retorno);

                })
                .catch(function (erro) {

                });
        }

        $scope.montDynamicTable = function (d) {

            var sortingOrder = 'cliente';

            $scope.sortingOrder = sortingOrder;
            $scope.pageSizes = [6, 12, 24, 48];
            $scope.reverse = false;
            $scope.filteredItems = [];
            $scope.groupedItems = [];
            $scope.itemsPerPage = 5;
            $scope.pagedItems = [];
            $scope.currentPage = 0;

            $scope.items = d;

            var searchMatch = function (haystack, needle) {
                if (!needle) {
                    return true;
                }

                if (haystack.toLowerCase === undefined) {
                    return false;
                }

                return haystack.toLowerCase().indexOf(needle.toLowerCase()) !== -1;
            };

            // init the filtered items
            $scope.search = function () {
                $scope.filteredItems = $filter('filter')($scope.items,
                    function (item) {
                        for (var attr in item) {
                            if (searchMatch(item[attr], $scope.query))
                                return true;
                        }
                        return false;
                    });
                // take care of the sorting order
                if ($scope.sortingOrder !== '') {
                    $scope.filteredItems = $filter('orderBy')(
                        $scope.filteredItems, $scope.sortingOrder,
                        $scope.reverse);
                }
                $scope.currentPage = 0;
                // now group by pages
                $scope.groupToPages();
            };

            // show items per page
            $scope.perPage = function () {
                $scope.groupToPages();
            };

            // calculate page in place
            $scope.groupToPages = function () {
                $scope.pagedItems = [];

                for (var i = 0; i < $scope.filteredItems.length; i++) {
                    if (i % $scope.itemsPerPage === 0) {
                        $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)] = [$scope.filteredItems[i]];
                    } else {
                        $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)]
                            .push($scope.filteredItems[i]);
                    }
                }
            };

            $scope.deleteItem = function (idx) {
                var itemToDelete = $scope.pagedItems[$scope.currentPage][idx];
                var idxInItems = $scope.items.indexOf(itemToDelete);
                $scope.items.splice(idxInItems, 1);
                $scope.search();

                return false;
            };

            $scope.range = function (start, end) {
                var ret = [];
                if (!end) {
                    end = start;
                    start = 0;
                }
                for (var i = start; i < end; i++) {
                    ret.push(i);
                }
                return ret;
            };

            $scope.prevPage = function () {
                if ($scope.currentPage > 0) {
                    $scope.currentPage--;
                    $(':checkbox').each(function () {
                        this.checked = false;
                    });
                }
            };

            $scope.nextPage = function () {
                if ($scope.currentPage < $scope.pagedItems.length - 1) {
                    $scope.currentPage++;
                    $(':checkbox').each(function () {
                        this.checked = false;
                    });
                }
            };

            $scope.setPage = function () {
                $scope.currentPage = this.n;
                $(':checkbox').each(function () {
                    this.checked = false;
                });
            };

            // functions have been describe process the data for display
            $scope.search();

            // change sorting order
            $scope.sort_by = function (newSortingOrder) {
                if ($scope.sortingOrder == newSortingOrder)
                    $scope.reverse = !$scope.reverse;

                $scope.sortingOrder = newSortingOrder;
            };
        }

        $().ready(function(){
          
            $scope.montDynamicTable($scope.mock);
        });
    });



