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
package fi.testee.examples.sample1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class AlertingFacade {
    private static final Logger LOG = LoggerFactory.getLogger(AlertingFacade.class);

    @Inject
    private AlertingService alertingService;

    public void alertIfUnavailable(String url) {
        LOG.info("Checking URL: {}", url);
        alertingService.alertIfUnavailable(url);
    }
}
