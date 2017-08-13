app.controller("LoadingIndicatorController", function ($scope, ServerService) {
    $scope.loading = false;
    ServerService.addListener({
        loadingStarted: function () {
            $scope.loading = true;
        },
        loadingFinished: function () {
            $scope.loading = false;
        }
    });
});
