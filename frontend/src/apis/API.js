import { api } from "./configs/axiosConfigs"
import { defineCancelApiObject } from "./configs/axiosUtils"

export const API = {
    get: async function (cancel = false) {
        const response = await api.request({
            url: `/test`,
            method: "GET",
            signal: cancel ? cancelApiObject[this.get.name].handleRequestCancellation().signal : undefined
        })

        return response.data
    }
}

const cancelApiObject = defineCancelApiObject(API)