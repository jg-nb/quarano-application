/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package quarano.delivery.web;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import quarano.core.web.MappingCustomizer;
import quarano.delivery.DeliveryImport;

import org.modelmapper.ModelMapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Johannes Griebenow
 */
@Component
@Order(10)
@RequiredArgsConstructor
class DeliveryImportMappingConfiguration implements MappingCustomizer {

	/*
	 * (non-Javadoc)
	 * @see quarano.core.web.MappingCustomizer#customize(org.modelmapper.ModelMapper)
	 */
	@Override
	public void customize(ModelMapper mapper) {

		mapper.addConverter(context -> {

			var deliveryimport = context.getSource();

			return new DeliveryImportDto();
		}, DeliveryImport.class, DeliveryImportDto.class);
	}
}
