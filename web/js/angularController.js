app.controller('myController', ['$scope', 'translationService',
    function ($scope, translationService) {

        //Выполняем перевод, если произошло событие смены языка
        $scope.translate = function () {
            translationService.getTranslation($scope, $scope.currentLocale);
        };
        // Инициализация
        $scope.currentLocale = 'ru';
        $scope.translate();

    }]);