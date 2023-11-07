import { postRequest, getRequest } from "@/api/axios";

export const dataTracerApi = {
    getDataTracerList: (params) => {
    return postRequest("/tracer", params);
  },

};
