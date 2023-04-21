<h1 align="center">Micro Ecommerce</h1>

<div align="center">

[![Status](https://img.shields.io/badge/status-active-success.svg)]()
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](/LICENSE)

</div>

<p align="center">
    Demo ecommerce application using various tech stacks
</p>

## 🧐 About <a name = "about"></a>

This project is a demo ecommerce application using MSA(Microservice Architecture) and various tech stacks.

The main purpose of this project is to experiment with various tech stacks, system / software architecture, design patterns and to learn the best practice of using them.

Tech stacks, system / software architecture chosen for this project are popular or used widely in the industry.

## ⛏️ Built Using <a name = "built_using"></a>

### SCM (Source Code Management)

<a href="https://git-scm.com">
    <img src="https://img.shields.io/badge/Git-F05032?logo=Git&logoColor=white" alt="Git" />
</a>
<a href="https://github.com">
    <img src="https://img.shields.io/badge/GitHub-181717?logo=GitHub&logoColor=white" alt="GitHub" />
</a>
<a href="https://pre-commit.com">
    <img src="https://img.shields.io/badge/pre--commit-FAB040?logo=pre-commit&logoColor=white" alt="pre-commit" />
</a>

<a href="https://editorconfig.org">
    <img src="https://img.shields.io/badge/EditorConfig-FEFEFE?logo=EditorConfig&logoColor=black" alt="EditorConfig" />
</a>

### CI / CD (Continuous Integration / Continuous Delivery)

<a href="https://github.com/features/actions">
    <img src="https://img.shields.io/badge/Github Actions-2088FF?logo=Github Actions&logoColor=white" alt="Github Actions" />
</a>
<a href="https://www.mend.io/renovate">
    <img src="https://img.shields.io/badge/Renovate-1A1F6C?logo=RenovateBot&logoColor=white" alt="Renovate" />
</a>

### Infrastructure
<a href="https://kubernetes.io">
    <img src="https://img.shields.io/badge/Kubernetes-326CE5?logo=Kubernetes&logoColor=white" alt="Kubernetes" />
</a>
<a href="https://www.rancher.com">
    <img src="https://img.shields.io/badge/Rancher-0075A8?logo=Rancher&logoColor=white" alt="Rancher" />
</a>
<a href="https://k3s.io">
    <img src="https://img.shields.io/badge/k3s-FFC61C?logo=k3s&logoColor=white" alt="k3s" />
</a>
<a href="https://helm.sh">
    <img src="https://img.shields.io/badge/Helm-0F1689?logo=helm&logoColor=white" alt="Helm" />
</a>
<a href="https://istio.io/latest/">
    <img src="https://img.shields.io/badge/Istio-466BB0?logo=Istio&logoColor=white" alt="Istio" />
</a>
<a href="https://cert-manager.io">
    <img src="https://img.shields.io/badge/Cert--Manager-FEFEFE" alt="Cert-Manager" />
</a>
<a href="https://www.keycloak.org">
    <img src="https://img.shields.io/badge/Keycloak-FEFEFE" alt="Keycloak" />
</a>

## 🏁 Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
See [deployment](#deployment) for notes on how to deploy the project on a live system.

### Prerequisites

1. Install [**Rancher Desktop**](https://rancherdesktop.io/)

    Rancher Desktop will install and configure the following tools:
    - [**Helm**](https://helm.sh/)
    - [**Docker**](https://www.docker.com/)
    - [**Kubectl**](https://kubernetes.io/docs/tasks/tools/install-kubectl/)

2. Install [**Skaffold**](https://skaffold.dev/docs/install/)

3. Install [**pre-commit**](https://pre-commit.com/#install)

### Installing / Running locally

1. Clone this repository

    ```bash
    git clone https://github.com/gudfhr95/micro-ecommerce
    cd micro-ecommerce
    ```

2. _(Optional)_ Install **pre-commit** hooks

    ```bash
    pre-commit install
    ```

3. Config `/etc/hosts`

    Add the following entries to your `/etc/hosts` file:

    ```bash
    127.0.0.1 httpbin.micro-ecommerce.com
    127.0.0.1 keycloak.micro-ecommerce.com
    ```

4. Install **Cert-Manager CRDs**(Custom Resource Definitions)

    Cert-Manager has a dependency on CRDs that need to be installed onto a Kubernetes cluster before Cert-Manager itself can be installed.

    ```bash
    kubectl apply -f https://github.com/cert-manager/cert-manager/releases/download/v1.11.1/cert-manager.crds.yaml
    ```

5. Run **Skaffold**

    ```bash
    skaffold dev
    ```

    All helm dependencies and charts will be installed automatically by Skaffold.

6. Access to **httpbin**

    ```bash
    open https://httpbin.micro-ecommerce.com/status/418
    ```

    Then you will see the following response:

    ```
    -=[ teapot ]=-
       _...._
     .'  _ _ `.
    | ."` ^ `". _,
    \_;`"---"`|//
      |       ;/
      \_     _/
        `"""`
    ```

7. Access to other services

    You can access other services by using the following URLs:
    - **Keycloak**: https://keycloak.micro-ecommerce.com

## 🖼️ Diagrams

### EventStorming

<img src="./diagrams/eventstorming.svg" alt="EventStorming"/>

### Context Map

<img src="./diagrams/contextmap.svg" alt="Context Map"/>

### System Architecture

<img src="./diagrams/system.svg" alt="System Architecture"/>

### Microservices

#### Terms

- SnapE

<img src="./diagrams/terms/snape.svg" alt="Terms SnapE"/>

- Aggregate

<img src="./diagrams/terms/aggregate.svg" alt="Terms Aggregate"/>

- ERD

<img src="./diagrams/terms/erd.svg" alt="Terms ERD"/>

## ✍️ Authors <a name = "authors"></a>

- [@gudfhr95](https://github.com/gudfhr95)

## 🎉 Acknowledgements
