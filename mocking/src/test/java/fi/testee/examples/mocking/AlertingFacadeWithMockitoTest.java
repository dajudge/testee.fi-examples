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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(TestEEfi.class)
public class AlertingFacadeWithMockitoTest {
    @Mock
    private HttpCheckingAdapter httpCheckingAdapter;
    @Mock
    private EmailDispatchAdapter emailDispatchAdapter;
    @Inject
    private AlertingFacade underTest;

    @Test
    public void dispatchesEmailWhenUnreachable() {
        // Given
        when(httpCheckingAdapter.checkUrl(any())).thenReturn(false);

        // When
        underTest.alertIfUnavailable("http://www.example.com");

        // Then
        verify(emailDispatchAdapter).sendEmail(
                "alex@it-stockinger.de",
                "http://www.example.com is down",
                "The URL http://www.example.com could not be reached"
        );
    }

    @Test
    public void notDispatchesEmailWhenReachable() {
        // Given
        when(httpCheckingAdapter.checkUrl(any())).thenReturn(true);

        // When
        underTest.alertIfUnavailable("http://www.example.com");

        // Then
        verify(emailDispatchAdapter, never()).sendEmail(any(), any(), any());
    }
}
