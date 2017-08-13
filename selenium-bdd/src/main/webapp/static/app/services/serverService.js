app.service("ServerService", function ($http) {
    var contextRoot = "/" + window.location.pathname.substring(1).split("/")[0];
    if (contextRoot === "/static") {
        contextRoot = "../rest";
    }

    var activeRequests = 0;
    var listeners = [];

    function inc() {
        if (activeRequests++ === 0) {
            listeners.forEach(function (l) {
                l.loadingStarted();
            });
        }
    }

    function dec() {
        if (--activeRequests === 0) {
            listeners.forEach(function (l) {
                l.loadingFinished();
            });
        }
    }

    this.addListener = function (listener) {
        listeners.push(listener);
    };

    function url(path) {
        return contextRoot + "/api" + path;
    }

    function execute(httpCall, success, error) {
        inc();
        httpCall.then(function (response) {
            try {
                success(response);
            } finally {
                dec();
            }
        }, function (response) {
            try {
                error(response);
            } finally {
                dec();
            }
        });
    }

    this.get = function (path, success, error) {
        execute($http.get(url(path)), success, error);
    };

    this.post = function (path, payload, success, error) {
        execute($http.post(url(path), payload), success, error);
    };

    this.delete = function (path, success, error) {
        execute($http.delete(url(path)), success, error);
    }
});