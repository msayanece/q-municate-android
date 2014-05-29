package com.quickblox.qmunicate.qb.commands;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.quickblox.module.chat.QBHistoryMessage;
import com.quickblox.qmunicate.core.command.ServiceCommand;
import com.quickblox.qmunicate.model.ChatCache;
import com.quickblox.qmunicate.qb.helpers.QBChatHelper;
import com.quickblox.qmunicate.service.QBService;
import com.quickblox.qmunicate.service.QBServiceConsts;

import java.util.List;

public class QBLoadDialogMessagesCommand extends ServiceCommand {

    private QBChatHelper chatHelper;

    public QBLoadDialogMessagesCommand(Context context, QBChatHelper chatHelper, String successAction,
            String failAction) {
        super(context, successAction, failAction);
        this.chatHelper = chatHelper;
    }

    public static void start(Context context, ChatCache chatCache, Object chatId) {
        Intent intent = new Intent(QBServiceConsts.LOAD_DIALOG_MESSAGES_ACTION, null, context,
                QBService.class);
        intent.putExtra(QBServiceConsts.EXTRA_CHAT_DIALOG, chatCache);
        intent.putExtra(QBServiceConsts.EXTRA_CHAT_ID, (java.io.Serializable) chatId);
        context.startService(intent);
    }

    @Override
    public Bundle perform(Bundle extras) throws Exception {
        ChatCache chatCache = (ChatCache) extras.getSerializable(QBServiceConsts.EXTRA_CHAT_DIALOG);
        Object chatId = extras.getSerializable(QBServiceConsts.EXTRA_CHAT_ID);
        List<QBHistoryMessage> dialogMessagesList = chatHelper.getDialogMessages(chatCache, chatId);
        Bundle bundle = new Bundle();
        bundle.putSerializable(QBServiceConsts.EXTRA_DIALOG_MESSAGES, (java.io.Serializable) dialogMessagesList);
        return bundle;
    }
}