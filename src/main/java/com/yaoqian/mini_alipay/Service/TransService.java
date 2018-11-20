package com.yaoqian.mini_alipay.Service;

import com.yaoqian.mini_alipay.entity.ResultEntity;

public interface TransService {

    /***
     * 转账
     * @param out_usrname
     * @param in_usrname
     * @param amount
     * @return ResultEntity
     * @throws Exception
     */
    ResultEntity Transfer(String out_usrname, String in_usrname, Float amount) throws Exception;

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
    void CreateRecord(String transUid, String transObjUid, Integer transType, Float amount, Integer transStatus, Integer transCategoryId, String transRemarks);

    /***
     * 创建一条成功的交易记录
     * @param transUid
     * @param transObjUid
     * @param transType
     * @param amount
     * @param transCategoryId
     */
    void SuccesRecord(String transUid, String transObjUid, Integer transType, Float amount, Integer transCategoryId);

    /***
     * 创建一条失败的交易记录
     * @param transUid
     * @param transObjUid
     * @param transType
     * @param amount
     * @param transCategoryId
     * @param transRemarks
     */
    void FailRecord(String transUid, String transObjUid, Integer transType, Float amount, Integer transCategoryId, String transRemarks);

    /***
     * 创建两条转账成功记录，收款人付款人各一条
     * @param tranOutUid
     * @param tranInUid
     * @param amount
     */
    void TwoSuccessTransferRecord(String tranOutUid, String tranInUid, Float amount);

    /***
     * 创建两条转账失败记录，收款人付款人各一条
     * @param transOutUid
     * @param transInUid
     * @param amount
     * @param transRemarks
     */
    void TwoFailTransferRecord(String transOutUid, String transInUid, Float amount, String transRemarks);
}
