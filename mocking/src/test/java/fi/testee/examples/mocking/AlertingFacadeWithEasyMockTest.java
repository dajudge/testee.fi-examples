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

import fi.testee.junit4.TestEEfi;
import org.easymock.Mock;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

@RunWith(TestEEfi.class)
public class AlertingFacadeWithEasyMockTest {
    @Mock
    private HttpCheckingAdapter httpCheckingAdapter;
    @Mock
    private EmailDispatchAdapter emailDispatchAdapter;
    @Inject
    private AlertingFacade underTest;

    @Test
    public void dispatchesEmailWhenUnreachable() {
        expect(httpCheckingAdapter.checkUrl("http://www.example.com")).andReturn(false).once();
        emailDispatchAdapter.sendEmail(
                "alex@it-stockinger.de",
                "http://www.example.com is down",
                "The URL http://www.example.com could not be reached"
        );
        expectLastCall().once();
        replay(httpCheckingAdapter, emailDispatchAdapter);

        underTest.alertIfUnavailable("http://www.example.com");

        verify(httpCheckingAdapter, emailDispatchAdapter);
    }

    @Test
    public void notDispatchesEmailWhenReachable() {
        expect(httpCheckingAdapter.checkUrl("http://www.example.com")).andReturn(true);
        replay(httpCheckingAdapter, emailDispatchAdapter);

        underTest.alertIfUnavailable("http://www.example.com");

        verify(httpCheckingAdapter, emailDispatchAdapter);
    }
}
