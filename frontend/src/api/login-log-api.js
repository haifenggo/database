import { postRequest, getRequest } from "@/api/axios";

export const loginLogApi = {
    getLoginLogList: (params) => {
    return postRequest("/loginLog", params);
  },

};
