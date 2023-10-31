import {postRequest, getRequest} from '@/api/axios';

export const userApi = {
    getUser: (userId) => {
        return getRequest(`/user/${userId}`);
    },
    getUserList: (param) => {
        return postRequest(`/user`, param);
    },
    updateUser: (user) => {
        return postRequest(`/user/update`, user);
    },
}

