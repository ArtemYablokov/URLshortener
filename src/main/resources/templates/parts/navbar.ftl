<#include "security.ftl">
<#import "loginform.ftl" as l>

<#-- navbar-expand-lg на больших экранах и более ПАНЕЛЬ будет в развернутом виде -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Farter</a> <#-- ссылка на главную страницу -->

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button> <#-- кнопка для экранов меньше чем LG -->

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/mess">Main page </a>
            </li>
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">User list </a>
            </li>
            </#if>
        </ul>

        <div class="navbar-text mr-3">${name}</div> <#-- как подвинуть имя к правому краю -->
        <@l.logout />
    </div>
</nav>

