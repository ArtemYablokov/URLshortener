<#macro login path isRegisterForm> <#-- path, isRegisterForm - переменные -->

    <form action="${path}" method="post">
        <div class="form-group row"> <#-- блок строки формы -->
            <label class="col-sm-2 col-form-label"> User Name : </label>
            <div class="col-sm-6"> <#-- блок поля ввода -->
                <input type="text" name="username" class="form-control" placeholder="User name"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password : </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm>
            <a href="/registration">Add new user</a>
        </#if>
        <button class="btn btn-primary" type="submit"> <#-- у кнопки LABEL описывается в виде контента - не нужен ДИВ -->
            <#if isRegisterForm>Create<#else>Sign In</#if>
        </button>

        <#--        <input type="text" name="username" value="" placeholder="User Name"><br>-->
        <#--        <input type="password" name="password" value="" placeholder="Password"><br>-->
        <#--        <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
        <#--        <input type="submit">-->
    </form>
</#macro>

<#macro logout>
    <div>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-primary" type="submit" <#--value="Sign Out"-->>Sign out</button>
        </form>
    </div>
</#macro>