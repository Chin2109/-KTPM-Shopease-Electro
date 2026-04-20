import { useForm, zodResolver } from '@mantine/form';
import CustomerGroupConfigs from 'pages/customer-group/CustomerGroupConfigs';
import { CustomerGroupRequest, CustomerGroupResponse } from 'models/CustomerGroup';
import useCreateApi from 'hooks/use-create-api';
import { SelectOption } from 'types';
import React from 'react';

function useCustomerGroupCreateViewModel() {
  const form = useForm({
    initialValues: CustomerGroupConfigs.initialCreateUpdateFormValues,
    schema: zodResolver(CustomerGroupConfigs.createUpdateFormSchema),
  });

  const createApi = useCreateApi<CustomerGroupRequest, CustomerGroupResponse>(CustomerGroupConfigs.resourceUrl);

  const handleFormSubmit = form.onSubmit((formValues) => {
    const requestBody: CustomerGroupRequest = {
      code: formValues.code,
      name: formValues.name,
      description: formValues.description,
      color: formValues.color,
      status: Number(formValues.status),
      minRewardPoint: formValues.minRewardPoint ? Number(formValues.minRewardPoint) : 0,
      discountPercent: formValues.discountPercent ? Number(formValues.discountPercent) : 0,
    };
    createApi.mutate(requestBody);
  });

  const statusSelectList: SelectOption[] = [
    {
      value: '1',
      label: 'Có hiệu lực',
    },
    {
      value: '2',
      label: 'Vô hiệu lực',
    },
  ];

  return {
    form,
    handleFormSubmit,
    statusSelectList,
  };
}

export default useCustomerGroupCreateViewModel;
