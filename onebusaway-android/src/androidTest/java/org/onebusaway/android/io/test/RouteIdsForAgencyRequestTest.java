/*
 * Copyright (C) 2010 Paul Watts (paulcwatts@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.io.test;

import org.junit.Test;
import org.onebusaway.android.io.request.ObaRouteIdsForAgencyRequest;
import org.onebusaway.android.io.request.ObaRouteIdsForAgencyResponse;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Tests requests and parsing JSON responses from /res/raw for the OBA server API
 * to get route_ids for an agency
 */
public class RouteIdsForAgencyRequestTest extends ObaTestCase {

    @Test
    public void testSoundTransit() {
        ObaRouteIdsForAgencyRequest.Builder builder =
                new ObaRouteIdsForAgencyRequest.Builder(getTargetContext(), "40");
        ObaRouteIdsForAgencyRequest request = builder.build();
        ObaRouteIdsForAgencyResponse response = request.call();
        assertOK(response);

        final String[] routeIds = response.getRouteIds();
        assertNotNull(routeIds);
        assertTrue(routeIds.length > 0);
        assertFalse(response.getLimitExceeded());
    }

    @Test
    public void testNewRequest() {
        // This is just to make sure we copy and call newRequest() at least once
        ObaRouteIdsForAgencyRequest request =
                ObaRouteIdsForAgencyRequest.newRequest(getTargetContext(), "40");
        assertNotNull(request);
    }
}
