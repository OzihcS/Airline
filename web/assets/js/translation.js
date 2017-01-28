var app = angular.module('myApp', ['ngResource']);

app.controller('myController', ['$scope', 'translationService', '$http',
    function ($rootScope, translationService, $http) {

        $http.get("http://localhost:8080/Airline/log/locale").then(function (response) {
            $rootScope.selectedLanguage = response.data.substring(1,3);
            $rootScope.translate();
        });

        $rootScope.translate = function () {
            $http.post("http://localhost:8080/Airline/log/locale?newLocale=" + $rootScope.selectedLanguage, {});
            translationService.getTranslation($rootScope, $rootScope.selectedLanguage);
        };
    }]);

app.service('translationService', function ($resource) {
    this.getTranslation = function (rootScope, language) {
        var languageFilePath = language + '.json';
        console.log(languageFilePath);
        $resource(languageFilePath).get(function (data) {
            rootScope.translation = data;
        });
    };
});