package com.yaoqian.mini_alipay.Service;

public interface TransService {

    /***
     * 创建一条交易记录
     * @param transUid
     * @param transObjUid
     * @param transType
     * @param amount
     * @param transStatus
     * @param transCategoryId
     * @param transRemarks
     */
    void CreateRecord(String transUid, String Trans_name, String transObjUid, String Trans_obj_name, Integer transType, Float amount, Integer transStatus, Integer transCategoryId, String transRemarks);

    /***
     * 创建一条成功的交易记录
     * @param transUid
     * @param transObjUid
     * @param transType
     * @param amount
     * @param transCategoryId
     */
    void SuccesRecord(String transUid, String Trans_name,String transObjUid, String Trans_obj_name, Integer transType, Float amount, Integer transCategoryId);

    /***
     * 创建一条失败的交易记录
     * @param transUid
     * @param transObjUid
     * @param transType
     * @param amount
     * @param transCategoryId
     * @param transRemarks
     */
    void FailRecord(String transUid, String Trans_name, String transObjUid, String Trans_obj_name, Integer transType, Float amount, Integer transCategoryId, String transRemarks);

    /***
     * 创建两条转账成功记录，收款人付款人各一条
     * @param tranOutUid
     * @param tranInUid
     * @param amount
     */
    void TwoSuccessTransferRecord(String tranOutUid, String Trans_name, String tranInUid, String Trans_obj_name, Float amount);

    /***
     * 创建两条转账失败记录，收款人付款人各一条
     * @param transOutUid
     * @param transInUid
     * @param amount
     * @param transRemarks
     */
    void TwoFailTransferRecord(String transOutUid, String Trans_name, String transInUid, String Trans_obj_name, Float amount, String transRemarks);
}
