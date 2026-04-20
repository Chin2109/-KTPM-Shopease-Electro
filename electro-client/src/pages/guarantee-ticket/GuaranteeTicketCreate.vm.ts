import { useForm, zodResolver } from "@mantine/form";

import useCreateApi from "hooks/use-create-api";
import { SelectOption } from "types";
import GuaranteeTicketConfigs from "./GuaranteeTicketConfig";
import { GuaranteeRequest, GuaranteeResponse } from "models/Guarantee";

function useGuaranteeCreateTicketViewModel() {
  const form = useForm({
    initialValues: GuaranteeTicketConfigs.initialCreateUpdateFormValues,
    schema: zodResolver(GuaranteeTicketConfigs.createUpdateFormSchema),
  });

  const createApi = useCreateApi<GuaranteeRequest, GuaranteeResponse>(
    GuaranteeTicketConfigs.resourceUrl,
  );

  const handleFormSubmit = form.onSubmit((formValues) => {
    const requestBody: GuaranteeRequest = {
      name: formValues.name,
      description: formValues.description || null,
      status: Number(formValues.status),
    };
    createApi.mutate(requestBody);
  });

  const statusSelectList: SelectOption[] = [
    {
      value: "1",
      label: "Có hiệu lực",
    },
    {
      value: "2",
      label: "Vô hiệu lực",
    },
  ];

  return {
    form,
    handleFormSubmit,
    statusSelectList,
  };
}

export default useGuaranteeCreateTicketViewModel;
