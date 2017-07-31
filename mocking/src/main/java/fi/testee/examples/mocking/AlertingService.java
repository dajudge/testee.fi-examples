/*
 * Copyright (C) 2017 Alex Stockinger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fi.testee.examples.mocking;

import javax.inject.Inject;

public class AlertingService {
    @Inject
    private HttpCheckingAdapter httpCheckingAdapter;
    @Inject
    private EmailDispatchAdapter emailDispatchAdapter;

    public void alertIfUnavailable(String url) {
        if (!httpCheckingAdapter.checkUrl(url)) {
            emailDispatchAdapter.sendEmail(
                    "alex@it-stockinger.de",
                    url + " is down",
                    "The URL " + url + " could not be reached"
            );
        }
    }
}
