var app = angular.module('myApp', ['ngResource']);

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

app.service('translationService', function ($resource) {
    this.getTranslation = function ($scope, language) {
        var languageFilePath = language + '.json';
        console.log(languageFilePath);
        $resource(languageFilePath).get(function (data) {
            $scope.translation = data;
        });
    };
});