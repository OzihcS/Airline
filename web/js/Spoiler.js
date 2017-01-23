var app = angular.module('myApp', ['ngResource']);
app.run(function ($rootScope, $http, $location) {
    $rootScope.changeLang = function (id) {
        $http.post("/locale?newLocale=" + id, {}).success(function (data, status) {
            $rootScope.locale = id;
            $rootScope.lang = data;
        })
    };

    $rootScope.flag = false;

    $rootScope.detailsData = {};

    $rootScope.defineLang = function () {
        $http.get("/locale", {}).success(function (data) {
            $rootScope.changeLang(data);
        })
    };

    $rootScope.checkLang = function (lang) {
        if (lang == $rootScope.lang['language']) {
            return true;
        }
    }
})
