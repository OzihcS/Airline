app.controller('myController', ['$scope', 'translationService',
    function ($scope, translationService) {

        //Выполняем перевод, если произошло событие смены языка
        $scope.translate = function () {
            translationService.getTranslation($scope, $scope.selectedLanguage);
        };
        // Инициализация
        $scope.selectedLanguage = 'en';
        $scope.translate();

    }]);