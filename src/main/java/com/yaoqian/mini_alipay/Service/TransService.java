package com.yaoqian.mini_alipay.Service;

import com.yaoqian.mini_alipay.entity.ResultEntity;

public interface TransService {

    ResultEntity Transfer(String out_usrname, String in_usrname, Float amount) throws Exception;

    void CreateRecord(String transUid, String transObjUid, Integer transType, Float amount, Integer transStatus, Integer transCategoryId, String transRemarks);

    void SuccesRecord(String transUid, String transObjUid, Integer transType, Float amount, Integer transCategoryId);

    void FailRecord(String transUid, String transObjUid, Integer transType, Float amount, Integer transCategoryId, String transRemarks);

    void TwoSuccessTransferRecord(String tranOutUid, String tranInUid, Float amount);

    void TwoFailTransferRecord(String transOutUid, String transInUid, Float amount, String transRemarks);
}
