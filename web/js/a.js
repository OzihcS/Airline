main.run(function ($rootScope, $http) {
    $rootScope.lang = {};
    $rootScope.changeLang = function (id) {
        $http.post("/locale?newLocale=" + id, {}).success(function (data, status) {
            $rootScope.locale = id;
            $rootScope.lang = data;
            console.log($rootScope.lang);
        })
    };

    $rootScope.defineLang = function () {
        $http.get("/locale", {}).success(function (data) {
            $rootScope.changeLang(data);
        })
    };

    $rootScope.checkLang = function (lang) {
        if (lang == $rootScope.lang['language']) {
            return true;
        }
    };
});
